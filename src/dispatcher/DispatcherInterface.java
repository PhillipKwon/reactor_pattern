package dispatcher;

import Handler.HandleMap;

import java.net.ServerSocket;

public interface DispatcherInterface {

    public void dispatch(ServerSocket serverSocket, HandleMap handleMap);
}
