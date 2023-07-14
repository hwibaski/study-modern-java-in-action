package me.hwibaski.modernjava._02_lambdaexpression.ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String s = processFile();

        // 한 행을 처리하는 코드
        processFileV2((BufferedReader br) -> br.readLine());

        // 두 행을 처리하는 코드
        processFileV2((BufferedReader br) -> br.readLine() + br.readLine());
    }

    /**
     * 실행 어라운드 패턴 (execute arround pattern)
     * <p>
     * 실제 자원을 처리하는 코드를 설정과 정리 두 과정이 둘러싸는 형태
     */
    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    public static String processFileV2(BufferedReaderProcessor b) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return b.process(br);
        }
    }
}
