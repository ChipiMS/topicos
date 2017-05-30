/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espotifai;

/**
 *
 * @author ELÍAS MANCERA
 */
public class Artist {

    int artistId;
    String name;

    public Artist(String name) {
        this.name = name;
    }

    public Artist(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return artistId + ""; //To change body of generated methods, choose Tools | Templates.
    }

}
