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
