package TreningsApp;

import java.util.Objects;

public class ForsteTreningsMåler {
    private String ovelse;
    private int vekt;
    private int reps;
    private int set;

    public String ForsteTreningsMåler(){
        return "Ikke løft";
    }
    public ForsteTreningsMåler(String ovelsem) {
        this.ovelse = ovelse;

    }
    public ForsteTreningsMåler(String ovelsem, int set) {
        this.ovelse = ovelse;
        this.set = set;
    }
    public ForsteTreningsMåler(String ovelsem, int set, int reps) {
        this.ovelse = ovelse;
        this.set = set;
        this.reps = reps;
    }
    public ForsteTreningsMåler(String ovelsem, int set, int reps, int vekt){
        this.ovelse = ovelse;
        this.set = set;
        this.reps = reps;
        this.vekt = vekt;
    }

    public String getOvelse() {
        return ovelse;
    }

    public void setOvelse(String ovelse) {
        this.ovelse = ovelse;
    }

    public int getVekt() {
        return vekt;
    }

    public void setVekt(int vekt) {
        this.vekt = vekt;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "ForsteTreningsMåler{" +
                "ovelse='" + getOvelse() + '\'' +
                ", vekt=" + getVekt() +
                ", reps=" + getReps() +
                ", set=" + getSet() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForsteTreningsMåler that = (ForsteTreningsMåler) o;
        return vekt == that.vekt && reps == that.reps && set == that.set && Objects.equals(ovelse, that.ovelse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ovelse, vekt, reps, set);
    }
}
