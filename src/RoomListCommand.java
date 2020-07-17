class RoomListCommand extends Command{
	private String[] listOfRooms;

	RoomListCommand(String[] list){
		this.listOfRooms = list;
	}

	public String execute(){
		for(int i = 0;i<listOfRooms.length;i++){
		System.out.println(listOfRooms[i]);
			}
	return "";
	}
}
