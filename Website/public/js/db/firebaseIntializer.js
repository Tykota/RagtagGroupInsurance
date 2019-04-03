// This script will connect to firebase if included in an html header

const config = {
    apiKey: "AIzaSyCMhdnqEpzfxFp9f8EyU0Seac69fYkmb7c",
    authDomain: "wizard-insurance.firebaseapp.com",
    databaseURL: "https://wizard-insurance.firebaseio.com",
    projectId: "wizard-insurance",
    storageBucket: "wizard-insurance.appspot.com",
    messagingSenderId: "348481372939"
};
firebase.initializeApp(config);
const db = firebase.firestore();
const storage = firebase.storage();

