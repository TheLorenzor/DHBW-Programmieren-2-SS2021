public enum produkttyp {
    Papier,
    Weis{
        @Override
        public String toString() {
            return "Weiss";
        }
    },
    Gruen{
        @Override
        public String toString() {
            return "Gruen";
        }
    }, Blau, A3,A4,A5,
    Holz, Kiefer, Buche, Eiche,Bretter,Balken,Scheit,
    Stein, Marmor, Granit, Sandstein, leicht, mittel, schwer
}
