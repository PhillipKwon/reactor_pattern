import Handler.*;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.InstantiationException;
import org.simpleframework.xml.core.Persister;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerInitializer {

    public static void main(String[] args) {
        int port = 50000;
        System.out.println("SERVER ON :: " + port);

        Reactor reactor = new Reactor(port);

        try {
            Serializer serializer = new Persister();
            File source = new File("HandlerList.xml");
            ServerListData serverListData = serializer.read(ServerListData.class, source);

            for (HandlerListData handlerListData : serverListData.getServer()) {
                if ("server1".equals(handlerListData.getName())) {
                    List<HandleData> handleData = handlerListData.getHandler();
                    for(HandleData handler : handleData) {
                        try {
                            reactor.registerHandler(handler.getHeader(), (EventHandler) Class.forName(handler.getHandler()).newInstance());
//                            reactor.registerHandler(handler.getHeader(), (EventHandler) Class.forName("Handler.StreamSayHelloEventHandler").newInstance());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // hander 직접 등록 Version
//        reactor.registerHandler(new StreamSayHelloEventHandler());
//        reactor.registerHandler(new StreamUpdateProfileEventHandler());

        reactor.startServer();

        // dispatcher Version
//        try {
//            ServerSocket serverSocket = new ServerSocket(port);
//            Socket connection;
//            Dispatcher dispatcher = new Dispatcher();

//            while (true) {
//                dispatcher.dispatch(serverSocket);
//                connection = serverSocket.accept();

//                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String line = bufferedReader.readLine();

//                System.out.println("READ :: " + line);
//            }

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
