# Compiling XSLT from zip file

Initial point of this the the try to make [xspec-maven-plugin](https://github.com/xspec/xspec-maven-plugin-1)
compatible with Saxon-HE 12.4, which is the current Saxon version supported by XSpec.

First pain point was loading and compiling various XSLTs from jar file, available on Maven Central repository.

This project is an extract from xspec-maven-plugin, limited to the problems encountered while compiling
Schematron framework.

## Unit tests

All unit-tests are expected to run with success.

There are 3 unit tests.

### `test_compile_from_directory_using_file_parameter`

In this unit test, we extract source files from original jar file (which is a zip file), and we compile
`io/xspec/xspec/impl/src/schematron/schut-to-xslt.xsl` with Saxon s9api `XsltCompiler.compile(File)`.

This unit test runs with success, as expected.

### `compileFromDirectoryUsingSource`

In this unit test, as in previous, we extract files from original jar file (which is a zip file), and we
compile `io/xspec/xspec/impl/src/schematron/schut-to-xslt.xsl` with Saxon s9api `XsltCompile.compile(Source)`,
where `source` is a `StreamSource` with correct `systemId` set on.

This unit test runs with success, as expected.

### `test_compile_from_jar_using_source_parameter`

In ths unit test, we do not extract files from jar file, we construct an `URI` that points to
`io/xspec/xspec/impl/src/schematron/schut-to-xslt.xsl` into the jar, something like
`jar:file:....!/io/xspec/xspec/impl/src/schematron/schut-to-xslt.xsl`. This kind of `URI` is supported by
JVM since 1.2, and we expect this compilation to succeed.

Based on ths above URI, a `StreamSource` is created, with the correct `systemId`, and compilation fails
when calling a `resolve-uri` function with `xml:base` set on the englobing `<xsl:sequence`.

There is no reason for this compilation to fail.

Unit-test expect no exception is thrown y compilation, but the expectation fails.
