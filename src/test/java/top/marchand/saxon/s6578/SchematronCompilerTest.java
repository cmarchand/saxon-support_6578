package top.marchand.saxon.s6578;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchematronCompilerTest {

  @Test
  @DisplayName("compiling from directory using File parameter should not throw exception")
  public void test_compile_from_directory_using_file_parameter() {
    SchematronCompiler compiler = new SchematronCompiler();
    Assertions
        .assertThatCode(() -> compiler.compileFromDirectoryUsingFile())
        .doesNotThrowAnyException();
  }
  @Test
  @DisplayName("compiling from directory using Source parameter should not throw exception")
  public void test_compile_from_directory_using_source_parameter() {
    SchematronCompiler compiler = new SchematronCompiler();
    Assertions
        .assertThatCode(() -> compiler.compileFromDirectoryUsingSource())
        .doesNotThrowAnyException();
  }
  @Test
  @DisplayName("compiling from jar using Source parameter should not throw exception")
  public void test_compile_from_jar_using_source_parameter() {
    SchematronCompiler compiler = new SchematronCompiler();
    Assertions
        .assertThatCode(() -> compiler.compileFromJarUsingSource())
        .doesNotThrowAnyException();
  }
}
