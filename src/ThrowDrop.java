class ThrowDrop extends Event{
    private String itemName;

    String callEvent(){
	GameState state = GameState.instance();
	state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
	state.getAdventurersCurrentRoom().add(state.getAdventurersCurrentRoom().getItemNamed(itemName));
	return "You threw something and it dropped.";
    }
}

