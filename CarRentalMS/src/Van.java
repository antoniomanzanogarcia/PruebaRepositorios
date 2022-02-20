
class Van extends Vehicle{

    private double rate=235;
    private double lateFee=299;

    Van(String vehicleId,int year,String make,String model,int status,VehicleType vehicleType)
    {
        super(vehicleId,year,make,model,status,vehicleType);
        this.rate=rate;
    }

    public double getLateFee(FechaHora endDate,FechaHora startDate){
        if(FechaHora.diffDays(endDate,startDate)>0)
            return this.lateFee* FechaHora.diffDays(endDate,startDate);
        else
            return this.lateFee=0.0;
    }

    public  boolean returnVehicle(FechaHora returnDate)
    {
        String vehicleType;
        if(this.Vehicle_id.contains("C_"))
            vehicleType="car";
        else
            vehicleType="van";
        if(this.vehicleStatus!=0)
        {
        FechaHora estimatedDate= this.records[this.getLastElementIndex()].getEstimatedReturnDate();
        FechaHora rentDate= this.records[this.getLastElementIndex()].getRentDate();

        if (vehicleType.equals("van") && FechaHora.diffDays(returnDate,rentDate)<1){
            return false;
        }
        else{
            double rent=this.rate* FechaHora.diffDays(returnDate,this.records[this.getLastElementIndex()].getRentDate())  ;
            this.records[this.getLastElementIndex()].setData(returnDate,rent,this.getLateFee(returnDate,estimatedDate));
            this.vehicleStatus=0;
            return true;
        }}else return false;
    }

    public boolean completeMaintenance(FechaHora completionDate)
    {
        if(this.vehicleStatus!=2 && FechaHora.diffDays(completionDate,this.vehicleType.getLastMaintenance())<12)
            return false;
        this.vehicleType.setLastMaintenance(completionDate);
        this.vehicleStatus=0;
        return true;
    }

    public String toString()
    {
        String details = super.toString();
        FechaHora lastMaintenanceDate= this.vehicleType.getLastMaintenance();
        details += ":"+ lastMaintenanceDate.toString();
        return details;
    }

    public String getDetails()
    {
        String details =super.getDetails();
        details+= "\nLast maintenance date:\t"+(this.vehicleType.getLastMaintenance()).toString();
        if(this.records[0]==null)
            details+="\nRENTAL RECORD:\tempty";
        else{
            details+="\nRENTAL RECORD:\n";
			int count=0;
			for(int index=0;this.records[index]!=null;index++)
				count++;
            for(int index=count-1;index>=0;index--)
            {
                details+= this.records[index].getDetails()+"                     \n";
                for(int innerIndex=0;innerIndex<10;innerIndex++)
                    details+= "-";
                details+= "                     \n";
            }
        }
        return details;
    }
	
	
}