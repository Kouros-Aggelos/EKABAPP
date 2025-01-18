const express = require('express');
const fetch = require('node-fetch');
const app = express();
const cors = require('cors');
app.use(express.json());
app.use(cors());  

// API Key της Google
const apiKey = 'AIzaSyCkgsJjyrx_oXY2WyjCagwEDCmXMO0f1Qo';

// Συνάρτηση για υπολογισμό της απόστασης
function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371;
    const dLat = Math.toRadians(lat2 - lat1);
    const dLon = Math.toRadians(lon2 - lon1);
    const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c; 
}

// Συνάρτηση για να πάρουμε τις συντεταγμένες από την διεύθυνση
async function getCoordinates(address) {
    const url = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${apiKey}`;
    const response = await fetch(url);
    const data = await response.json();

    if (data.status === 'OK') {
        const location = data.results[0].geometry.location;
        return { lat: location.lat, lng: location.lng };
    }
    return { lat: 0, lng: 0 };
}

// POST αιτημα για να βρούμε το κοντινότερο νοσοκομείο
app.post('/get-closest-hospital', async (req, res) => {
    const userAddress = req.body.address;

    try {
        const userCoordinates = await getCoordinates(userAddress);

        if (userCoordinates.lat === 0 || userCoordinates.lng === 0) {
            return res.json({ status: 'failure', message: 'Αποτυχία λήψης συντεταγμένων για τη διεύθυνση χρήστη.' });
        }
        };

        res.json({
            status: 'success',
            data: closestHospital
        });

    } catch (error) {
        console.error(error);
        res.json({ status: 'failure', message: 'Σφάλμα κατά την αναζήτηση του κοντινότερου νοσοκομείου.' });
    }
});

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
