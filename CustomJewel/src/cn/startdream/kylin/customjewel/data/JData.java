package cn.startdream.kylin.customjewel.data;

import java.util.List;

public class JData {

    private int id;
    private int data;
    private String name;
    private List<String> lore;
    private List<String> type;
    private List<String> addLore;
    private String identify;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getAddLore() {
        return addLore;
    }

    public void setAddLore(List<String> addLore) {
        this.addLore = addLore;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
}
