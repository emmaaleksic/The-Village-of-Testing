public interface Building {
    String getName();

    int getWoodCost();

    int getMetalCost();

    int getDaysWorkerOn();

    void setDaysWorkerOn(int daysWorkerOn);

    int getDaysToComplete();

    void removeOneDayToComplete();

    boolean isComplete();

    void setComplete(int daysGone);

    boolean enoughResources(int wood, int metal);
}
