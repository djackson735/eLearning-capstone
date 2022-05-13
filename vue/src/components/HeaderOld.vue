


<template>
  <div id="banner">
    <main id="wrapper" class="banner col ps-md-2 pt-2">
    <div id="bannerleft">
        <img id="img" src="@/assets/skull.jpg">
            Header</div>
    <div id="bannerright">
        <div id="homebtn"><router-link v-bind:to="{ name: 'home' }">Home</router-link></div>
        <div class="hidden" v-if="$store.state.token != ''&isLoaded"></div><div id="logoutbtn" v-if="$store.state.token != ''&isLoaded"><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''&isLoaded">Logout</router-link></div></div>
    </main>
    <div id="subheader">
        <div id="subheaderleft">
        </div>
        <div id="subheaderright">
          <a href="#" data-bs-target="#wrapper" data-bs-toggle="collapse" class="border rounded-3 p-1 text-decoration-none"><i class="bi bi-list bi-lg py-2 p-1"></i> Menu</a>
            <div id="userinfo"  v-if="loggedIn&isLoaded">
                <div id="elmnt0">Logged in as {{ this.$store.state.user.username }}</div>
            </div>
        </div>
     </div>
  </div>
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
  },
  data() {
    return {
      pageload: false,
      isLoaded: false,
      user: {
      },
      errorMsg: '',
    }
  },
};
</script>
<style scoped>
#img {
    width: 70px;
    border-radius: 50%;
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
    display: flex;
    width: 100%;
    font-style: italic;
    font-family: Impact;
    font-size: 44pt;
    color: white;
    text-shadow: 2px 2px black;
    padding-left: 24px;
    padding-bottom: 8px;
    padding-top: 4px;
}
#bannerright {
    display: float;
    width: 150px;
    align-content: right;
    text-align: center;
    font-size: 1.5em;
    text-decoration: none;
    font-family: Helvetica;
    font-weight: bold;
    padding-right: 6px;
}
#hidden {
    width: 100%;
    height: 35px;
    border-radius: 4px 4px 4px 4px;
}
#homebtn {
    margin-top: 7px;
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
    background-color: white;
    border-radius: 4px 4px 4px 4px;
    color: #42c2f5;
}
#logoutbtn:hover {
    width: 100%;
    background-color: white;
    border-radius: 4px 4px 4px 4px;
}
#bannerright a:link {
    color: white;
}
#bannerright a:visited {
    color: white;
}
#bannerright a:active {
    color: #42c2f5;
}
#bannerright a:hover {
    color: #42c2f5;
}
#subheader {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    height: 26px;
    flex-basis: 100%;
    background-color: white;
}
#subheaderleft {
    display: flex;
    float: left;
    align-items: center;
    height: 27px;
    flex-basis: 300px;
    border-left: 2px #42c2f5 solid;
}
#subheaderright {
    display: flex;
    float: right;
    align-items: center;
    justify-content: right;
    height: 24px;
    flex-basis: 100%;
    border-bottom: 2px #42c2f5 solid;
    border-right: 2px #42c2f5 solid;
    color: purple;
}
#elmnt0 {
    padding-left: 10px;
    width: 40;
    color: purple;
    padding-right: 5px;
}
</style>