var app
$(function(){
	if($("#product-list").length > 0) {
		applyVue({
			el: "#product-list",
			data: {
				products: dataSource,
				mssage: ""
			},
			methods: {
				removeProduct: function(id) {
					ajaxwork("DELETE", "deleteProductData/" + id, "", (callback) => {
						if(callback.message == "success") {
							document.location.reload();
						}
					});
				},
				updateProduct: function(id, name, detail, kind, price) {
					$.blockUI({ message: "<form id='updateProduct'>" +
							"<input disabled='disabled' type='text' name='id' value='" + id + "' />" +
							"<input type='hidden' name='id' value='" + id + "' />" +
							"<input type='text' name='name' value='" + name + "' />" +
							"<input type='text' name='detail' value='" + detail + "' />" +
							"<input type='text' name='kind' value='" + kind + "' />" +
							"<input type='text' name='price' value='" + price + "' />" +
							"<input type='button' value='update' />" +
							"</form>" });

				}
			}
		});
	} else if($("#product-single").length > 0) {
		applyVue({
			el: "#product-single",
			data: {
				name: "",
				detail: "",
				price: 0,
				status: "",
				question: ''
			},
			watch: {
				question: function() {
					this.status = "typeing";
					this.getProduct();
				}
			},
			methods: {
				getProduct: _.debounce(function () {
					var _id = $("#product-single input[type=text]").val();
					ajaxwork("GET", "productDataId/" + _id, "", (callback) => {
						if(callback == "error") {
							this.status = "NOT FOUND";
						} else {
							this.name = callback.product.name;
							this.detail = callback.product.detail;
							this.price = callback.product.price;
							this.status = "";
						}
					});
				}, 1000)
			}
		});
	} else {
		
	}
	
	if($("#add-product").length > 0) {
		$("#add-product form input[type=button]").click(function(){
			var formdata = $("#add-product form").serializeArray();
			var data = {};
			$(formdata ).each(function(index, obj){
				if(obj.name=="id" || obj.name=="price") {
					data[obj.name] = parseInt(obj.value);
				} else {
					data[obj.name] = obj.value;
				}
			    
			});
			
			ajaxwork("POST", "newProductData", JSON.stringify(data), function(callback) {
				alert(callback.message);
				document.location.reload();
			});
		});
		
	}
	
	$(document).on("click", "form#updateProduct input[type=button]" ,function(){
		var formdata = $("form#updateProduct").serializeArray();
		var data = {};
		$(formdata ).each(function(index, obj){
			if(obj.name=="id" || obj.name=="price") {
				data[obj.name] = parseInt(obj.value);
			} else {
				data[obj.name] = obj.value;
			}
		    
		});

		
		ajaxwork("PUT", "updateProductData", JSON.stringify(data), function(callback) {
			alert(callback.message);
			document.location.reload();
		});
		
		
	});
	
 });

function applyVue(_data) {
	app = new Vue(_data);
}

function ajaxwork(_type, _url, _data, _func) {
	$.ajax({
		type: _type,
		url: _url,
		data: _data,
		contentType: 'application/json',
        mimeType: 'application/json',
		success: function(msg) {
			if(typeof _func === "function") {
				_func(msg);
			}
		}, error: function() {
			if(typeof _func === "function") {
				_func("error");
			}
		}
	});
}