new Vue({
  el: '#app',
  data: {
  	sourceCurrency: 'BRL',
    targetCurrency: 'JPY',
    value: 10,
    result: ''
  },
  methods: {
  	submit() {
    	let url = '/exchange/from/';
      	url += this.sourceCurrency + '/to/' + this.targetCurrency + '/value/' + this.value;

      this.$http
          .get(url)
          .then(response => this.result = response.bodyText).catch(function(e) {
            this.result = 'ERROR HTTP STATUS - ' + e.status;
        });
    }
  }
})