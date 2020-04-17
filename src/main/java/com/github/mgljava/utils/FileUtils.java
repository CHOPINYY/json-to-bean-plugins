package com.github.mgljava.utils;

import java.io.File;

public final class FileUtils {

  private FileUtils() {
  }

  public static void mkdirs(String dir) {
    File f = new File(dir);
    if (!f.getParentFile().exists()) {
      f.getParentFile().mkdirs();
    }
  }
}
