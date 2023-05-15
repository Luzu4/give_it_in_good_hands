document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            if (this.currentStep === 1) {
                if (!document.forms["donationForm"]["categories"]) {
                    alert("categories cannot be null!!");
                    this.currentStep--;
                    return false;
                }
            }
            if (this.currentStep === 3) {
                var quantity = document.forms["donationForm"]["quantity"].value;
                if (quantity == null || quantity === "") {
                    alert("quantity cannot be null!!");
                    this.currentStep--;
                    return false;
                }
            }
            var street = document.forms["donationForm"]["street"].value;
            if (this.currentStep === 5) {
                if (street == null || street === "") {
                    this.currentStep--;
                    alert("Street cannot be empty!!");
                    return false;
                }
            }
            var city = document.forms["donationForm"]["city"].value;
            if (this.currentStep === 5) {
                if (city == null || city === "") {
                    this.currentStep--;
                    alert("City cannot be empty!!");
                    return false;
                }
            }
            var zipCode = document.forms["donationForm"]["zipCode"].value;
            if (this.currentStep === 5) {
                if (zipCode == null || zipCode === "") {
                    this.currentStep--;
                    alert("zipCode cannot be empty!!");
                    return false;
                }
            }
            var pickUpDate = document.forms["donationForm"]["pickUpDate"].value;
            if (this.currentStep === 5) {
                if (pickUpDate == null || pickUpDate === "") {
                    this.currentStep--;
                    alert("pickUpDate cannot be empty!!");
                    return false;
                }
            }
            var pickUpTime = document.forms["donationForm"]["pickUpTime"].value;
            if (this.currentStep === 5) {
                if (pickUpTime == null || pickUpTime === "") {
                    this.currentStep--;
                    alert("pickUpTime cannot be empty!!");
                    return false;
                }
            }
            var phoneNumber = document.forms["donationForm"]["phoneNumber"].value;
            if (this.currentStep === 5) {
                if (phoneNumber == null || phoneNumber === "" || phoneNumber.length < 9) {
                    this.currentStep--;
                    alert("phoneNumber cannot be empty and need to have 9digits!!");
                    return false;
                }
            }


            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary
        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }

    const button = document.querySelector("#toConfButton");
    if (button) {
        button.addEventListener("click", function () {
                document.getElementById('confPickUpCity').innerText = document.getElementById('city').value;
                document.getElementById('confPickUpStreet').innerText = document.getElementById('street').value;
                document.getElementById('confPickUpZipCode').innerText = document.getElementById('zipCode').value;
                document.getElementById('confPickUpPhoneNumber').innerText = document.getElementById('phoneNumber').value;
                document.getElementById('confPickUpComment').innerText = document.getElementById('pickUpComment').value;
                document.getElementById('confPickUpDate').innerText = document.getElementById('pickUpDate').value;
                document.getElementById('confPickUpTime').innerText = document.getElementById('pickUpTime').value;
                document.getElementById('confQuantity').innerText = document.getElementById('quantity').value;
                const select = document.querySelector('input[name="institution"]:checked');
                if (select) {
                    let selectTitle = select.parentElement.querySelector(".title");
                    document.getElementById('confInstitution').innerText = ' ' + selectTitle.innerHTML;
                } else {
                    document.getElementById('confInstitution').innerText = ' ' + "I don't care who will get it";
                }

                const checkboxes = document.querySelectorAll('input[type=checkbox][name="categories"]:checked');
                checkboxes.forEach((checkbox) => {
                    document.getElementById('confQuantity').innerText = document.getElementById('confQuantity').innerText + " " + checkbox.parentElement.querySelector(".description").innerHTML;
                });
            }
        )
    }
});
