public class ThrowVanish extends Event{
    private String itemName;

    String callEvent(){
	GameState state = GameState.instance();
	state.removeFromInventory(state.getItemFromInventoryNamed(itemName));
    }
}
