# Οδηγίες για το στήσιμο του RestFul WEB Api του Stingy

- Τρέξτε στην mySQL το __database.ddl__ ώστε να δημιουργηθεί η βάση του Stingy.
- Δημιουργήστε στην βάση ένα __user με όνομα softeng και κωδικό softeng__.
- Τρέξτε __sudo ./gradle apprun__ για να γίνει το build του API και να σηκωθεί ο server.
- Στην διεύθυνση __http://localhost:8765/observatory/api/__ είναι διαθέσιμο το API μας με την προδιαγραφές που έχουν ζητηθεί.

# Οδηγίες για τον έλεγχο του RestFul WEB Api του Stingy
- Με χρήση του extension του chrome restlet client https://chrome.google.com/webstore/detail/restlet-client-rest-api-t/aejoelaoggembcahagimdiliamlcdmfm.
- Με χρήση του εργαλείου Postman https://www.getpostman.com/.
- Και στις δύο περιπτώσεις διαλέγουμε την μέθοδο που θέλουμε (get, post, put, patch), το uri που θέλουμε και εισάγουμε ανάλογα τιμές ως
παραμέτρους ή στο body (ανάλογα με τα specification του API). Προσοχή, ότι σε μεθόδους post, put, patch όπως αναφέρεται στις 
προδιαγραφές πρέπει να σταλθεί και στο header το token του χρήστη ή του administrator.
