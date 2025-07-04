package org.tk.acc_cus.validation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class ExtractCustomerNumbers {
    public static void main(String[] args) throws IOException {
        Path out = Paths.get("CustomerId_2096.csv");
        PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(out));

        File file = new File("BANK2096");
        AtomicLong count = new AtomicLong();
        try (Stream<String> stringStream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            stringStream.forEach(line ->  {
                String lineWithCusId = line.trim();
                if ( Character.isDigit(lineWithCusId.charAt(1)) & lineWithCusId.contains(".")) {
                    count.getAndIncrement();
                    lineWithCusId = lineWithCusId.substring(0, lineWithCusId.indexOf("."));
                    printWriter.println(lineWithCusId);
                    //if (count.get() == 10) System.exit(0);
                }
            });
        }
        System.out.println("Total Customer Id's = " + count.get());
        printWriter.close();
    }

}
