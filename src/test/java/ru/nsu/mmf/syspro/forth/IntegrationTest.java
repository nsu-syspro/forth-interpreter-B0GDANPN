package ru.nsu.mmf.syspro.forth;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegrationTest {

    @Test
    public void testAll() throws URISyntaxException, IOException {
        URL resourceURL = getClass().getResource("/testData");
        if (resourceURL == null) {
            throw new IllegalStateException("Could not find testData folder in the resources");
        }
        Path testData = Path.of(resourceURL.toURI());
        TestRunner testRunner = new TestRunner();
        StringBuilder errors = new StringBuilder();
        try (Stream<Path> testFiles = Files.list(testData)) {
            testFiles.filter(testFile -> testFile.toString().endsWith(".test"))
                    .forEach(testFile -> testOne(testRunner, errors::append, testFile));
        }
        if (!errors.isEmpty()) {
            throw new TestFailure(errors.toString());
        }
    }

    private static void testOne(@NotNull TestRunner testRunner, @NotNull ErrorCollector errorCollector, @NotNull Path testFile) {
        try {
            TestCase testCase = TestCase.create(testFile);
            testCase.check(testRunner);
        } catch (TestFailure e) {
            errorCollector.collect(e.getMessage());
            errorCollector.collect("-".repeat(100));
            errorCollector.collect("\n");
        }
    }

    private static @NotNull String error(@NotNull String testName, @NotNull String msg) {
        return """
                Unexpected test file format in test '%s': %s.
                Expected format:
                in:
                <input_data>
                       
                out:
                <output_data>
                """.formatted(testName, msg);
    }

    private static class TestRunner {
        String run(@NotNull String input) {
            InputStream oldIn = System.in;
            PrintStream oldOut = System.out;
            try {
                System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
                ByteArrayOutputStream newOutStream = new ByteArrayOutputStream();
                System.setOut(new PrintStream(newOutStream));
                Main.main(new String[]{});
                return newOutStream.toString(StandardCharsets.UTF_8);
            } finally {
                System.setIn(oldIn);
                System.setOut(oldOut);
            }
        }
    }

    private static class TestFailure extends AssertionError {
        TestFailure(String message) {
            super(message);
        }
    }

    private static class TestFormatException extends TestFailure {
        public TestFormatException(String message) {
            super(message);
        }
    }

    private static class TestComparisonFailure extends TestFailure {
        public TestComparisonFailure(String msg, String expected, String actual) {
            super(error(msg, expected, actual));
        }

        private static String error(String msg, String expected, String actual) {
            return """
                    Comparison failure: %s
                    Expected:
                    %s
                    Actual:
                    %s
                    """.formatted(msg, expected, actual);
        }
    }

    private interface ErrorCollector {
        void collect(String message);
    }

    private record TestCase(@NotNull String name, @NotNull String in, @NotNull String expected) {

        @Contract("_ -> new")
        private static @NotNull TestCase create(@NotNull Path path) {
            List<String> lines;
            try {
                lines = Files.readAllLines(path);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

            String testName = path.getFileName().toString();
            int nLines = lines.size();
            if (nLines < 2) {
                throw new TestFormatException(error(testName, "too few lines"));
            }
            if (!lines.get(0).trim().equals("in:")) {
                throw new TestFormatException(error(testName, "expected 'in:' as a first line"));
            }

            int i = 1;
            StringBuilder input = new StringBuilder();
            while (i != nLines) {
                String line = lines.get(i);
                if (line.trim().equals("out:")) {
                    break;
                }
                input.append(line).append("\n");
                i++;
            }

            input.append("exit\n");

            if (i >= nLines) {
                throw new TestFormatException(error(testName, "expected 'out:' line"));
            }

            if (i == nLines - 1) {
                return new TestCase(testName, input.toString(), "");
            }

            String output = String.join("\n", lines.subList(i + 1, nLines));
            return new TestCase(testName, input.toString(), output);
        }

        private static @NotNull String trimInputPrompt(@NotNull String s) {
            int pos = 0;
            if (s.startsWith(">")) {
                do {
                    pos++;
                } while (pos < s.length() && s.charAt(pos) == ' ');
            }
            return s.substring(pos);
        }

        public void check(@NotNull TestRunner runner) {
            String actual = runner.run(in).lines()
                    .map(TestCase::trimInputPrompt)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.joining("\n"));

            if (!Objects.equals(expected, actual)) {
                throw new TestComparisonFailure("Different outputs for test " + name, expected, actual);
            }
        }
    }
}
