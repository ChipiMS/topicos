package espotifai;
public class PlayListTrack {
    int id;
    int trackId;

    public PlayListTrack(int id, int trackId) {
        this.id = id;
        this.trackId = trackId;
    }
    public PlayListTrack(int trackId){
        this.trackId = trackId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
    
}
