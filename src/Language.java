class Language {
    // singleton

    private lang languageSet;
    private static Language instance;

    private Language () { languageSet = lang.DE; }

    static Language getInstance () {
        if (Language.instance == null) {
            Language.instance = new Language ();
        }
        return Language.instance;
    }

    lang getSelectedLanguage() { return languageSet; }
}
