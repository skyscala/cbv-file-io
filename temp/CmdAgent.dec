/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdwk2.engine.dms;

import cmdwk2.engine.util.HttpPostUtil;
import cmdwk2.engine.util.JsonUtil;
import cmdwk2.engine.util.HttpPostUtil.PostResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author zlhso
 */

public class CmdAgent implements JavaDelegate {

    private static final Logger log=Logger.getLogger(CmdAgent.class.getName());
    private static final String CMDROUTE = "cmdUrl";

    @Override
    public void execute(DelegateExecution exec) throws Exception {

        Map<String, Object> variables = exec.getVariables();
        Map<String, Object> transactionData = new HashMap<>();

        Method[] methods = DelegateExecution.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                ReflectionUtils.makeAccessible(method);
                Object data=ReflectionUtils.invokeMethod(method, exec);
                if(data instanceof String){
                    putData(method.getName().replaceFirst("get", ""), data, transactionData);
                }
            }
        }
        transactionData.putAll(variables);
        Object cmdLink = transactionData.get(CMDROUTE);
        if (cmdLink != null) {
            postTransactionData(cmdLink.toString().trim(), transactionData, exec);
        }

    }

    private void putData(String k, Object val, Map<String, Object> target) {
        target.put(k, val);
    }

    private void postTransactionData(String route, Map<String, Object> data, DelegateExecution exec) {
        try {
            
            log.log(Level.INFO, "Route - {0}", route);
            PostResponse postResponse = HttpPostUtil.postJson(route, data);
            int resCode=postResponse.getStatusCode();
            log.log(Level.INFO, "Response Code - {0}", resCode);
            if (resCode!=200) {
                throw new Exception("CMD call was fail.");
            }
            Date now = Calendar.getInstance().getTime();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMMddHHmmss");
            exec.setVariable("cmdResult_" + fmt.format(now), JsonUtil.toJsonString(postResponse));

        } catch (Exception ex) {
            log.log(Level.INFO, "Error while invoking cmd - {0}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    
   
}
