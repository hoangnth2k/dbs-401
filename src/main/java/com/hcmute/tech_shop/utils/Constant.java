package com.hcmute.tech_shop.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constant {
    public static Locale vietnam = Locale.of("vi", "VN");
    // Định dạng số thành tiền tệ Việt Nam Đồng
    public static NumberFormat formatter = NumberFormat.getCurrencyInstance(vietnam);
}
