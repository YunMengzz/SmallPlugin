package cn.kylin.llw;

public class LimitData {

    private String worldName;
    private String levelName;
    private int level;

    public LimitData() {
    }

    public LimitData(String worldName, String levelName, int level) {
        this.worldName = worldName;
        this.levelName = levelName;
        this.level = level;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
