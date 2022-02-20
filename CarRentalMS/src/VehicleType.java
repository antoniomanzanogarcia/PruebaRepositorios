
class VehicleType {

    private int carSeats;
    private int vanSeats=15;
    private FechaHora LastMaintenance;
    private String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    VehicleType(int seats)
    {
        this.carSeats=seats;
    }

    VehicleType(int seats,FechaHora LastMaintenance){
        this.vanSeats=seats;
        this.LastMaintenance=LastMaintenance;
    }

    public int getSeats(String type)
    {
        if(type.equals("car")){
            return this.carSeats;
        }
        else{
            return this.vanSeats;
        }
    }


    public int getCarSeats()
    {
        return this.carSeats;
    }


    public void setCarSeats(int seats)
    {
        this.carSeats=seats;
    }

    private int indexOf(String day){
        for(int index=0;index<days.length;index++)
            if(days[index].equals(day))
                return index;
        return -1;
    }

    public FechaHora getLastMaintenance(){
        return this.LastMaintenance;
    }

    public void setLastMaintenance(FechaHora date)
    {
        this.LastMaintenance=date;
    }
   
    public int canBeRentedForMinimumDays(FechaHora date,String type)
    {
        if(this.indexOf(date.getNameOfDay())+1<=5 && this.indexOf(date.getNameOfDay())+1>=1 && type.equals("car")){
            return 2;
        }
        else if(type.equals("car")){
            return 3;
        }
        else{
            return 1; //van can be rented only 1 day
        }
    }

    public boolean IsUnderMaintenance(FechaHora rentDate,String type,int numOfRentDays)
    {
        FechaHora nextMaintenance=new FechaHora(this.LastMaintenance,12);
        if(type.equals("van") && FechaHora.diffDays(nextMaintenance,new FechaHora(rentDate,numOfRentDays))>=0 && numOfRentDays<=12)
        {
            return false;
        }
		if(type.equals("car"))
		{
			return false;
		}
        else
        {
            return true;
        }
    }
}

