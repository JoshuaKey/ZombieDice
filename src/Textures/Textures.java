package Textures;

import javafx.scene.image.Image;

public class Textures {
	
	private Image GreenFootprint;
	private Image YellowFootprint;
	private Image RedFootprint;
	private Image GreenSurvivor;
	private Image YellowSurvivor;
	private Image RedSurvivor;
	private Image GreenShotgun;
	private Image YellowShotgun;
	private Image RedShotgun;
	
	public Textures(){
		//FootprintRed
		GreenFootprint = new Image("Images/FootprintGreen.png");
		YellowFootprint = new Image("Images/FootprintYellow.png");
		RedFootprint = new Image("Images/FootprintRed.png");
		GreenSurvivor = new Image("Images/SurvivorsGreen.png");
		YellowSurvivor = new Image("Images/SurvivorsYellow.png");
		RedSurvivor = new Image("Images/SurvivorsRed.png");
		GreenShotgun = new Image("Images/ShotgunGreen.png");
		YellowShotgun = new Image("Images/ShotgunYellow.png");
		RedShotgun = new Image("Images/ShotgunRed.png");
	}
	
	public Image getGreenFootprintImg() {
		return GreenFootprint;
	}

	public Image getYellowFootprintImg() {
		return YellowFootprint;
	}

	public Image getRedFootprintImg() {
		return RedFootprint;
	}

	public Image getGreenSurvivorImg() {
		return GreenSurvivor;
	}

	public Image getYellowSurvivorImg() {
		return YellowSurvivor;
	}

	public Image getRedSurvivorImg() {
		return RedSurvivor;
	}

	public Image getGreenShotgunImg() {
		return GreenShotgun;
	}

	public Image getYellowShotgunImg() {
		return YellowShotgun;
	}

	public Image getRedShotgunImg() {
		return RedShotgun;
	}

}
