let map, geocoder, userMarker, directionsService, directionsRenderer;
let hospitals = [
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
        { position: { lat: 38.079905336267686, lng: 23.770074595703807 }, title: "Γ.Ο.Ν.Κ. Άγιοι Ανάργυροι" },
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
        { position: { lat: 37.98494513940935, lng: 23.768890366862518 }, title: "Αγ. Σοφία" },
        { position: { lat: 37.97984903959627, lng: 23.754288109189886 }, title: "Αρεταίειο" },
        { position: { lat: 37.97214084045193, lng: 23.661937695698047 }, title: "Αγ. Παντελεήμονας" }
    ];

function initMap() {
    // Δημιουργία του χάρτη
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 37.9768, lng: 23.7433 }, // Κεντρικές συντεταγμένες
        zoom: 10,
    });

    // Δημιουργία του geocoder
    geocoder = new google.maps.Geocoder();

    // Δημιουργία του directions service και renderer για να δείξουμε τη διαδρομή
    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer();
    directionsRenderer.setMap(map);

    // Δημιουργία του marker του χρήστη (μπλε)
    userMarker = new google.maps.Marker({
        map: map,
        draggable: true,
        icon: {
            url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png", // Μπλε χρώμα για το marker του χρήστη
        }
    });

    // Δημιουργία markers για τα νοσοκομεία με κόκκινο χρώμα
    hospitals.forEach(hospital => {
        new google.maps.Marker({
            position: hospital.position,
            map: map,
            title: hospital.title,
            icon: {
                url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png", // Κόκκινο χρώμα για τα markers των νοσοκομείων
            }
        });
    });

    // Βρίσκουμε τη θέση του χρήστη
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const userLocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            userMarker.setPosition(userLocation);
            map.setCenter(userLocation);

            // Υπολογισμός της κοντινότερης απόστασης και διαδρομής
            findClosestHospital(userLocation, hospitals);
        }, function() {
            alert("Η τοποθεσία δεν μπόρεσε να εντοπιστεί.");
        });
    } else {
        alert("Η γεωεντοπισμός δεν υποστηρίζεται από τον περιηγητή σας.");
    }
}

function findClosestHospital(userLocation, hospitals) {
    let closestHospital = null;
    let minDistance = Infinity;

    // Βρίσκουμε το κοντινότερο νοσοκομείο
    hospitals.forEach(hospital => {
        const hospitalLocation = hospital.position;
        const distance = google.maps.geometry.spherical.computeDistanceBetween(
            new google.maps.LatLng(userLocation.lat, userLocation.lng),
            new google.maps.LatLng(hospitalLocation.lat, hospitalLocation.lng)
        );

        if (distance < minDistance) {
            minDistance = distance;
            closestHospital = hospital;
        }
    });

    // Υπολογισμός της διαδρομής προς το κοντινότερο νοσοκομείο
    if (closestHospital) {
        const request = {
            origin: userLocation,
            destination: closestHospital.position,
            travelMode: google.maps.TravelMode.DRIVING
        };

        directionsService.route(request, function(result, status) {
            if (status === google.maps.DirectionsStatus.OK) {
                directionsRenderer.setDirections(result);
            } else {
                alert("Δεν μπόρεσε να υπολογιστεί η διαδρομή.");
            }
        });
    }
}

// Συνάρτηση για geocoding διεύθυνσης
function geocodeAddress() {
    const address = document.getElementById('address').value;
    geocoder.geocode({ 'address': address }, function(results, status) {
        if (status === 'OK') {
            const userLocation = results[0].geometry.location;
            userMarker.setPosition(userLocation);
            map.setCenter(userLocation);

            // Υπολογισμός της κοντινότερης απόστασης και διαδρομής
            findClosestHospital({
                lat: userLocation.lat(),
                lng: userLocation.lng()
            }, hospitals);
        } else {
            alert('Δεν βρέθηκε η διεύθυνση: ' + status);
        }
    });
}



