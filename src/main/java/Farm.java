public class Farm implements Building{

    private final String name;
    private final int woodCost;
    private final int metalCost;
    private int daysWorkerOn;
    private int daysToComplete;
    private boolean complete;

    public Farm(String name)
    {
        this.name = name;
        this.woodCost = 5;
        this.metalCost = 2;
        this.daysWorkerOn = 0;
        this.daysToComplete = 5;
        this.complete = false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getWoodCost() {
        return 0;
    }

    @Override
    public int getMetalCost() {
        return 0;
    }

    @Override
    public int getDaysWorkerOn() {
        return 0;
    }

    @Override
    public void setDaysWorkerOn(int daysWorkerOn) {

    }

    @Override
    public int getDaysToComplete() {
        return 0;
    }

    @Override
    public void removeOneDayToComplete() {

    }


    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void setComplete(int daysGone) {

    }

    @Override
    public boolean enoughResources(int wood, int metal) {
        return false;
    }

}
