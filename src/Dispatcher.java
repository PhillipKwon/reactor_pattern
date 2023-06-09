import Handler.HandleMap;
import protocol.StreamSayHelloProtocol;
import protocol.StreamUpdateProfileProtocol;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher {

    public void dispatch(ServerSocket serverSocket, HandleMap handleMap) {
        try {
            Socket socket = serverSocket.accept();

            Runnable demultiplexer = new Demultiplexer(socket, handleMap);
            Thread thread = new Thread(demultiplexer);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
