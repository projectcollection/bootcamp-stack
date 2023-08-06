import React, { useRef, useEffect, useState } from 'react';
import mapboxgl from '!mapbox-gl'; // eslint-disable-line import/no-webpack-loader-syntax

export default function MapApp() {
    mapboxgl.accessToken = 'pk.eyJ1IjoiZGhlZXJqdGhlZGV2MSIsImEiOiJjbGRldDVqZmEwZ3Y5M3JxcDFkbTR6bXZ2In0.WIAyIRDeVTI7nVTLy1GeMg';
    const [stateChange, setStateChange] = useState(0)
    const [lng, setLng] = useState(-70.9);
    const [lat, setLat] = useState(42.35);
    const [zoom, setZoom] = useState(9);
    const onClick = () => {
        console.log("Inside the on click")
        const currentstate = stateChange
        console.log("Inside the on click--- after the state change")
        if (currentstate === 0) {
            console.log("Inside the on click--- the state is zero ")
            setStateChange(1);
        }
        else {
            console.log("Inside the on click--- the state is one  ")
            setStateChange(0);
        }
    }

    useEffect(() => {
        console.log("The Map Is Initialised once more ")
        //if (map.current) return; // initialize map only once
        map.current = new mapboxgl.Map({
          container: mapContainer.current,
          style: 'mapbox://styles/mapbox/streets-v12',
          center: [lng, lat],
          zoom: zoom
        });
      }, [stateChange]);

    const mapContainer = useRef(null);
    const map = useRef(null);
    
    return (
        <>
        <button className='btn btn-secondary' onClick={onClick}>Refresh</button>
        <div>
          <div ref={mapContainer} className="map-container" />
        </div>
        </>
    );
}