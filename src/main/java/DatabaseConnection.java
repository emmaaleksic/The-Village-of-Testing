public class DatabaseConnection {

    public void save(Village village){
        System.out.println("Saved to database");
    }
    public Village load(){
        System.out.println("Load from database");
        return new Village(this);
    }


}
