package Handler;

import java.io.InputStream;

public interface EventHandler {

    // Hnadler의 Key값을 Return하는 Method
    public String getHandler();

    // 데이터를 받아 처리하는 Method
    public void handleEvent(InputStream inputStream);
}
