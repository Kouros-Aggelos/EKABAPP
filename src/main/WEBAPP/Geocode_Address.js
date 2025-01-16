const express = require('express');
const fetch = require('node-fetch');
const app = express();
app.use(express.json());

app.post('/get-coordinates', async (req, res) => {
    const address = req.body.address;
    const apiKey = 'AIzaSyCkgsJjyrx_oXY2WyjCagwEDCmXMO0f1Qo'; 
    const url = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${apiKey}`;

    try {
        const response = await fetch(url);
        const data = await response.json();

        if (data.status === 'OK') {
            const location = data.results[0].geometry.location;
            const lat = location.lat;
            const lng = location.lng;

            // Εδώ επιστρέφει μόνο τα lat και lng
            res.json({ lat, lng });

            useCoordinatesInOtherFunction(lat, lng);

        } else {
            res.status(404).json({ message: 'Address not found' });
        }
    } catch (error) {
        res.status(500).json({ message: 'Server error' });
    }
});

// Παράδειγμα χρήσης των συντεταγμένων σε άλλη συνάρτηση ή κλάση
function useCoordinatesInOtherFunction(lat, lng) {
    console.log("Latitude:", lat, "Longitude:", lng);

}

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});