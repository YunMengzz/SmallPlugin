package cn.kylin.onlinegive;

import java.util.List;

public class OnlineData {

    private List<String> cmds;
    private String msg;


    public OnlineData() {
    }

    public OnlineData(List<String> cmds, String msg) {
        this.cmds = cmds;
        this.msg = msg;
    }

    public List<String> getCmds() {
        return cmds;
    }

    public void setCmds(List<String> cmds) {
        this.cmds = cmds;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
