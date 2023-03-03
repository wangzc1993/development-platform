package org.wangzc.development.platform.model.api.engine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nutz.lang.util.NutMap;
import org.wangzc.development.platform.model.api.engine.tag.FunctionTag;

public class ApiContext {

    private Map<String, Object> argsMap = new LinkedHashMap<>();

    private Map<String, List<String>> argsArea = new LinkedHashMap<>();

    private Map<String, FunctionTag> functionMap = new LinkedHashMap<>();

    private List<String> logs = new ArrayList<>();

    private NutMap apiParameters = new NutMap();

    public void addArgsArea(String id) {
        System.out.println("添加作用域：" + id);
        argsArea.put(id, new ArrayList<>());
    }

    public void removeArgsArea(String id) {
        System.out.println("删除作用域：" + id);
        List<String> list = argsArea.get(id);
        for (String s : list) {
            System.out.println("删除作用域变量：" + s);
            argsMap.remove(s);
        }
        argsArea.remove(id);
        System.out.println("删除作用域完成----");
    }

    public void setFunction(String name, FunctionTag tag) {
        functionMap.put(name, tag);
    }

    public void removeFunction(String name) {
        functionMap.remove(name);
    }

    public FunctionTag getFunction(String name) {
        return functionMap.get(name);
    }

    public void setArg(String key, Object value) {
        if (argsMap.containsKey(key)) {
            System.out.println("覆盖变量：" + key);
            argsMap.put(key, value);
            return;
        }
        List<String> ls = new ArrayList<>(argsArea.keySet());
        String id = ls.get(ls.size() - 1);
        argsArea.get(id).add(key);
        argsMap.put(key, value);
        System.out.println("添加变量：" + key + " - " + id);
    }

    public Object getArg(String key) {
        return argsMap.get(key);
    }

    public Map<String, Object> getArgsMap() {
        return argsMap;
    }

    public void addLog(String log) {
        logs.add(log);
    }

    public List<String> getLogs() {
        return logs;
    }

    public NutMap getApiParameters() {
        return apiParameters;
    }

    public void setApiParameters(NutMap apiParameters) {
        this.apiParameters = apiParameters;
    }


    
}
