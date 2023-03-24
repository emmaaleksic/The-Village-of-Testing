public class Castle implements Building{

    private final String name;
    private final int woodCost;
    private final int metalCost;
    private int daysWorkerOn;
    private int daysToComplete;
    private boolean complete;

    public Castle(String name) {
        this.name = name;
        this.woodCost = 50;
        this.metalCost = 50;
        this.daysWorkerOn = 0;
        this.daysToComplete = 50;
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
        daysWorkerOn++;
    }


    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public void setComplete(int daysGone) {
        complete=true;
        System.out.println("The castle is complete! You won! It took " + daysGone + " days to build the castle");
    }

    @Override
    public boolean enoughResources(int wood, int metal) {
        if (wood >= woodCost && metal >= metalCost) {
            return true;
        }
        return false;
    }


}
