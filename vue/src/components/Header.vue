<template>
  <b-navbar type="dark" variant="info" id="banner">
    <b-navbar-brand href="#">
      <img id="img" src="@/assets/skull.jpg" class="d-inline-block align-top" alt="Dungeon Lab Wizard Logo">
    </b-navbar-brand>
    <b-navbar-item id="bannerleft">Dungeon Labs</b-navbar-item>
    <b-navbar-nav class="ms-auto">
      <b-button-group>
        <b-button id="sideBarToggle" v-b-toggle.sidebar>Toggle Sidebar</b-button>
        <b-button id="homebtn" variant="light" v-bind:to="{ name: 'home' }">Home</b-button>
        <b-button id="logoutbtn" variant="light" :to="{ name: 'logout' }" v-if="$store.state.token !== ''&isLoaded">Logout
        </b-button>
        <b-dd-item-button variant="light" id="userinfo" v-if="loggedIn&isLoaded">
        <div id="elmnt0">Logged in :
          <br>{{ this.$store.state.user.username }}</div>
      </b-dd-item-button>
      </b-button-group>
    </b-navbar-nav>
  </b-navbar>
</template>
<script>

export default {
  created() {
    this.isLoaded = true;
  },
  computed: {
    loggedIn() {
      return this.user.id !== ''
    },
    loggedInMessage() {
      return this.loggedIn() ? 'Logged in as ' + this.$store.state.user.username : '';
    }
  },
  data() {
    return {
      pageload: false,
      isLoaded: false,
      user: {},
      errorMsg: '',
    }
  },
};
</script>
<style scoped>
#img {
  width: 4em;
  border-radius: 50%;
  padding-left: 10px;
  vertical-align: middle;
}

#banner {
  width: 100%;
  border-collapse: collapse;
  background-color: #42c2f5;
  z-index: 3;
}
#wrapper {
  display: flex;
}
#bannerleft {
  display: -ms-flexbox;
  width: 100%;
  font-style: italic;
  font-family: Impact;
  font-size: 30pt;
  color: white;
  text-shadow: 2px 2px black;
  padding-left: 15px;
}
/*#bannerright {*/
/*  display: -ms-flexbox;*/
/*  width: 150px;*/
/*  align-content: inherit;*/
/*  text-align: center;*/
/*  font-size: 1.5em;*/
/*  text-decoration: none;*/
/*  font-family: Helvetica;*/
/*  font-weight: bold;*/
/*  padding-right: 6px;*/
/*}*/
/*#hidden {*/
/*  width: 100%;*/
/*  height: 35px;*/
/*  border-radius: 4px 4px 4px 4px;*/
/*}*/
#homebtn {
  /*margin-top: 7px;*/
  width: 100%;
  height: 35px;
  border-radius: 4px 4px 4px 4px;
}
#logoutbtn {
  width: 100%;
  height: 35px;
  border-radius: 4px 4px 4px 4px;
}
#homebtn:hover {
  width: 100%;
  margin-right: 10px;
  background-color: white;
  border-radius: 4px 4px 4px 4px;
  color: #42c2f5;
}
#logoutbtn:hover {
  width: 100%;
  margin-right: 10px;

  background-color: white;
  border-radius: 4px 4px 4px 4px;
}
#sideBarToggle {
  width: 100%;

  margin-right: 10px;
  background-color: #c0c0c0;
  border-radius: 4px 4px 4px 4px;
}
/*#bannerright a:link {*/
/*  color: white;*/
/*}*/
/*#bannerright a:visited {*/
/*  color: white;*/
/*}*/
/*#bannerright a:active {*/
/*  color: #42c2f5;*/
/*}*/
/*#bannerright a:hover {*/
/*  color: #42c2f5;*/
/*}*/
/*#subheader {*/
/*  display: flex;*/
/*  align-items: center;*/
/*  justify-content: flex-start;*/
/*  height: 26px;*/
/*  flex-basis: 100%;*/
/*  background-color: white;*/
/*}*/
/*#subheaderleft {*/
/*  display: flex;*/
/*  float: left;*/
/*  align-items: center;*/
/*  height: 27px;*/
/*  flex-basis: 300px;*/
/*  border-left: 2px #42c2f5 solid;*/
/*}*/
/*#subheaderright {*/
/*  display: flex;*/
/*  float: right;*/
/*  align-items: center;*/
/*  justify-content: right;*/
/*  height: 24px;*/
/*  flex-basis: 100%;*/
/*  !*border-bottom: 2px #42c2f5 solid;*!*/
/*  border-right: 2px #42c2f5 solid;*/
/*  color: purple;*/
/*}*/
/*#elmnt0 {*/
/*  padding-left: 10px;*/
/*  width: 40;*/
/*  color: purple;*/
/*  padding-right: 5px;*/
/*}*/
</style>