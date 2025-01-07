<<<<<<< HEAD:ekab.js
function initMap() {
    // Δημιουργία του χάρτη
    const map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 37.9768, lng: 23.7433 }, // Κεντρικές συντεταγμένες
        zoom: 10, // Επίπεδο zoom
        mapId: '2f4496367dbf70bc'
    });

    // Δημιουργία markers για τα νοσοκομεία
    const hospitals = [
     
        { position: { lat: 38.037999023010904, lng: 23.757447111046247 }, title: "Γ.Ν.Ν.ΙΩΝΙΑΣ ΚΩΝ/ΠΟΥΛΕΙΟ" },
        { position: { lat: 37.99726391243294, lng: 23.782734111040604 }, title: "Γ.Ν.Α. Γ. Γεννηματάς" },
        { position: { lat: 37.98053270577266, lng: 23.75491176686225 }, title: "Γ.Ν.Α. Αλεξάνδρα" },
        { position: { lat: 37.99524844183412, lng: 23.77951561104386 }, title: "Γ.Ν.Α. Σωτηρία" },
        { position: { lat: 37.934485573858964, lng: 23.645577453368304 }, title: "Γ.Ν.Π. Τζάνειο" },
        { position: { lat: 38.01596528716577, lng: 23.665232439881265 }, title: "Π.Γ.Ν. Αττικόν" },
        { position: { lat: 38.046362110183004, lng: 23.82830821104667 }, title: "Γ.Ν.Α. Σισμανόγλειο" },
        { position: { lat: 37.97765258351495, lng: 23.74666925151744 }, title: "Γ.Ν.Α. Ευαγγελισμός" },
        { position: { lat: 38.0660559147875, lng: 23.808382924539217 }, title: "Γ.Ν.Α. ΚΑΤ" },
        { position: { lat: 37.84846152543729, lng: 23.754899626380826 }, title: "Γ.Ν. Ασκληπιείο Βούλας" },
        { position: { lat: 37.9834127354887, lng: 23.765280838026303 }, title: "Γ.Ν.Α. Λαϊκό" },
        { position: { lat: 38.079905336267686, lng: 223.770074595703807 }, title: "Γ.Ο.Ν.Κ. Άγιοι Ανάργυροι" },
        { position: { lat: 37.988135008135764, lng: 23.75558193591389 }, title: "Α.Ο.Ν.Α. Άγιος Σάββας" },
        { position: { lat: 37.97405040166575, lng: 23.75180382453426 }, title: "Ν.Δ.Ν.Α. Α. Σύγγρος" },
        { position: { lat: 37.985131896555146, lng: 23.755248798329937 }, title: "Γ.Ν.Μ. Ελ. Βενιζέλου" },
        { position: { lat: 37.97990823689677, lng: 23.754277380353845 }, title: "Π.Γ.Ν.Α. Αρεταίειο" },
        { position: { lat: 37.98539189457194, lng: 23.767847295698754 }, title: "Γ.Ν.Π.Α. Αγλαΐα Κυριακού" },
        { position: { lat: 38.05106509737491, lng: 23.875143795702282 }, title: "Γ.Ν. Παίδων Πεντέλης" },
        { position: { lat: 37.99085507245576, lng: 23.755025135905996 }, title: "Ν.Α. Οφθαλμιατρείο Αθηνών" },
        { position: { lat: 38.013336918462144, lng: 23.640769124536387 }, title: "Ψ.Ν.Α. Δαφνί" },
        { position: { lat: 38.000719379180765, lng: 23.662581894364857 }, title: "Ψ.Ν.Α. Δρομοκαΐτειο" },
        { position: { lat: 37.97941350885422, lng: 23.7537402597085 }, title: "Ν.Π. Αιγινήτειο" },
        { position: { lat: 37.92996959741673, lng: 23.64350316685954 }, title: "Ε.Α.Ν.Π. Μεταξά" },
        { position: { lat: 37.98372296250003, lng: 23.759771156604263 }, title: "Ιπποκράτειο" },
        { position: { lat: 37.99001331758793, lng: 23.755381353371344 }, title: "Ελπίς" },
        { position: { lat: 38.01448759410462, lng: 23.73161742453642 }, title: "Παμμακάριστος" },
        { position: { lat: 37.98494513940935,lng: 23.768890366862518 }, title: "Αγ. Σοφία" },
        { position: { lat: 37.97984903959627, lng: 23.754288109189886 }, title: "Αρεταίειο" },
        { position: { lat: 37.97214084045193, lng: 23.661937695698047 }, title: "Αγ. Παντελεήμονας" }
    ];

    // Δημιουργία markers στον χάρτη
    hospitals.forEach(hospital => {
        new google.maps.marker.AdvancedMarkerElement({
            map: map,
            position: hospital.position,
            title: hospital.title,
        });
    });
}

=======
function initMap() {
    // Δημιουργία του χάρτη
    const map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 37.9768, lng: 23.7433 }, // Κεντρικές συντεταγμένες
        zoom: 10, // Επίπεδο zoom
        mapId: '2f4496367dbf70bc'
    });

    // Δημιουργία markers για τα νοσοκομεία
    const hospitals = [
        { position: { lat: 37.9768, lng: 23.7433 }, title: "Ευαγγελισμός" },
        { position: { lat: 38.0480, lng: 23.8352 }, title: "Σισμανόγλειο" },
        { position: { lat: 37.9804, lng: 23.7558 }, title: "Λαϊκό" },
        { position: { lat: 37.9893, lng: 23.7676 }, title: "Γ. Γεννηματάς" },
        { position: { lat: 37.9865, lng: 23.7551 }, title: "Η Αγία Σοφία" },
        { position: { lat: 37.9423, lng: 23.6854 }, title: "ΗΡΑ" },
        { position: { lat: 38.0724, lng: 23.8291 }, title: "ΚΑΤ" },
        { position: { lat: 37.9850, lng: 23.7490 }, title: "Αλεξάνδρα" },
        { position: { lat: 37.9891, lng: 23.6629 }, title: "Αγία Βαρβάρα" },
        { position: { lat: 37.9871, lng: 23.7513 }, title: "Ιπποκράτειο" },
        { position: { lat: 37.9773, lng: 23.7324 }, title: "Οφθαλμιατρείο" },
        { position: { lat: 37.9865, lng: 23.7551 }, title: "Παίδων" },
        { position: { lat: 37.9837, lng: 23.7252 }, title: "Πολυκλινική" },
        { position: { lat: 37.9651, lng: 23.6249 }, title: "Δρομοκαΐτειο" },
        { position: { lat: 37.9925, lng: 23.7345 }, title: "Ελπίς" },
        { position: { lat: 37.9914, lng: 23.7572 }, title: "Η Σωτηρία" },
        { position: { lat: 37.9826, lng: 23.7380 }, title: "Μπενάκειο" },
        { position: { lat: 38.0414, lng: 23.5248 }, title: "Θριάσιο" },
    ];

    // Δημιουργία markers στον χάρτη
    hospitals.forEach(hospital => {
        new google.maps.marker.AdvancedMarkerElement({
            map: map,
            position: hospital.position,
            title: hospital.title,
        });
    });
}
>>>>>>> b744e98f6360221ccf47dd74345981472b54205e:src/main/webapp/ekab.js
