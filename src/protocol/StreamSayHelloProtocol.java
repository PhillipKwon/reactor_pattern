package protocol;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class StreamSayHelloProtocol {

    private static final int DATA_SIZE = 512;
    private static final int TOKEN_NUM = 2;

    public void handleEvent(InputStream inputStream) {

        try {
            byte[] buffer = new byte[DATA_SIZE];
            inputStream.read(buffer);
            String data = new String(buffer);

            String[] params = new String[TOKEN_NUM];
            StringTokenizer tokenizer = new StringTokenizer(data, "|");

            int i = 0;
            while (tokenizer.hasMoreTokens()) {
                params[i] = tokenizer.nextToken();
                ++i;
            }

            sayHello(params);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHello(String[] params) {
        System.out.println("[Protocol] SayHello -> name : " + params[0] + "| age : " + params[1]);
    }
}
