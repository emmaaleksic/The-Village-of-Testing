public class Woodmill implements Building{

    private final String name;
    private final int woodCost;
    private final int metalCost;
    private int daysWorkerOn;
    private int daysToComplete;
    private boolean complete;

    public Woodmill(String name)
    {
        this.name = name;
        this.woodCost = 5;
        this.metalCost = 1;
        this.daysWorkerOn = 0;
        this.daysToComplete = 5;
        this.complete = false;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getWoodCost() {
        return woodCost;
    }

    @Override
    public int getMetalCost() {
        return metalCost;
    }

    @Override
    public int getDaysWorkerOn() {
        return daysWorkerOn;
    }

    @Override
    public void setDaysWorkerOn(int daysWorkerOn) {this.daysWorkerOn = daysWorkerOn;}

    @Override
    public int getDaysToComplete() {
        return daysToComplete;
    }

    @Override
    public void removeOneDayToComplete() {
        daysToComplete--;
    }


    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public void setComplete(int daysGone) {

    }

    @Override
    public boolean enoughResources(int wood, int metal) {
        if(wood >= woodCost && metal >= metalCost){
            return true;}
        return false;
    }


}
