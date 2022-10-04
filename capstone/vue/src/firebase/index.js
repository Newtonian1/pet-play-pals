import { initializeApp } from 'firebase/app';
import { getDatabase } from 'firebase/database';
import { getStorage } from 'firebase/storage'

const firebaseConfig = {
  apiKey: 'AIzaSyCF761UUIgthZYgEDoUwUdSp_o_IPGnf04',
  authDomain: 'petplaypals-a7a47.firebaseapp.com',
  databaseURL: 'https://petplaypals-a7a47-default-rtdb.firebaseio.com',
  projectId: 'petplaypals-a7a47',
  storageBucket: 'petplaypals-a7a47.appspot.com',
  messagingSenderId: '550417461118',
  appId: '1:550417461118:web:7769c9ce06592543f4cd2f',
  measurementId: 'G-LL5MPH26SW'
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const fireDb = getDatabase(app);
export const fireStorage = getStorage(app);