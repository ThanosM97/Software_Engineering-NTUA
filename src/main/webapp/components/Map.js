import React, { Component } from 'react'
import Head from 'next/head';

class Map extends Component {

  state = {
    venues: []
  }

  componentDidMount() {
    this.renderMap()
  }

  renderMap = () => {
    loadScript("https://maps.googleapis.com/maps/api/js?key=AIzaSyAMZm-VfcJmBlUcfm-OZGltQDgujt8-HEM&callback=initMap")
    window.initMap = this.initMap
  }

  initMap = () => {

    // Create A Map
    var map = new window.google.maps.Map(document.getElementById('map'), {
      center: {lat:this.props.lat, lng:this.props.lng},
      zoom: 15
    })

    // Create An InfoWindow
    var infowindow = new window.google.maps.InfoWindow()


      // Create A Marker
      var marker = new window.google.maps.Marker({
        position: {lat:this.props.lat, lng:this.props.lng},
        map: map
      })

      // Click on A Marker!
      marker.addListener('click', function() {

        // Change the content
        //infowindow.setContent(contentString)

        // Open An InfoWindow
        infowindow.open(map, marker)
      })
  }

  render() {
    return (
      <main>
        <Head>
          <link href="../static/Map.css" rel="stylesheet" />
        </Head>
        <div id="map"></div>
      </main>
    )
  }
}

function loadScript(url) {
  var index  = window.document.getElementsByTagName("script")[0]
  var script = window.document.createElement("script")
  script.src = url
  script.async = true
  script.defer = true
  index.parentNode.insertBefore(script, index)
}

export default Map;