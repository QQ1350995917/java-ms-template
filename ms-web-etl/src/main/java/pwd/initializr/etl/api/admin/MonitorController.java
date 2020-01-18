package pwd.initializr.etl.api.admin;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pwd.initializr.etl.ETLApplicationContextAware;
import pwd.initializr.etl.business.admin.MonitorService;
import pwd.initializr.etl.business.admin.ReportTask;

/**
 * pwd.initializr.etl.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-18 21:01
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ServerEndpoint("/monitor")
@Component
public class MonitorController {

  private MonitorService monitorService = ETLApplicationContextAware.getBean(MonitorService.class);

  @OnOpen
  public void onOpen(Session session, @PathParam("sid") String sid) {
    monitorService.onOpen(session,sid);
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    monitorService.onMessage(message,session);
  }

  @OnError
  public void onError(Session session, Throwable error) {
    monitorService.onError(session,error);
  }

  @OnClose
  public void onClose() {
    monitorService.onClose();
  }
}
