package classes;

import java.util.ArrayList;

public class Realisateur extends Personne {

	private ArrayList<Video> videos;

	public Realisateur(String nom, String prenom) {
		super(nom, prenom);
		this.videos = new ArrayList<Video>();
	}

	public ArrayList<Video> getVideos() {
		return videos;
	}

	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}

	public void AjouterVideo(Video v) {
		this.videos.add(v);
		v.setRealisateur(this);
	}

}
