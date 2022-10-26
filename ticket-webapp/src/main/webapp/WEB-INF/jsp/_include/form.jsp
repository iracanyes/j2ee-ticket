<%--
  Description: 
  Date: 20-10-22  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%--
  form.method must specifies the request method of the form

 form.action must specifies the URI which will handle the request.
 Here, the request will be send to the uri "/forms"
--%>
<form method="post" action="forms" class="d-flex flex-column">
  <fieldset>
    <legend>Form - input tag</legend>
    <fieldset>
      <legend>Form - input text</legend>
      <div class="form-group">
        <label for="firstname">Firstname : </label>
        <input type="text" id="firstname" name="firstname">
      </div>
      <div class="input-group">
        <label for="lastname">Lastname : </label>
        <input type="text" id="lastname" name="firstname">
      </div>
      <div class="input-group">
        <label for="passwordLabel">Password : </label>
        <input type="password" id="passwordLabel" name="password">
      </div>
      <div class="input-group">
        <label for="aNum">Number : </label>
        <input type="number" id="aNum" name="aNum" />
      </div>
      <div class="input-group">
        <label for="lastname">Preferred color : </label>
        <input type="color" id="color" name="color">
      </div>
      <div class="input-group">
        <label for="dateLabel">Date : </label>
        <input type="date" id="dateLabel" name="myDate">
      </div>
      <div class="input-group">
        <label for="myTimeLabel">Time : </label>
        <input type="time" id="myTimeLabel" name="myTime">
      </div>
      <div class="input-group">
        <label for="datetime">Date and time : </label>
        <input type="datetime-local" id="datetime" name="datetime" />
      </div>
    </fieldset>
    <fieldset class="datalist">
      <legend>Datalist </legend>
      <label for="ice-cream-choice">Choose a flavor:</label>
      <input list="ice-cream-flavors" id="ice-cream-choice" name="ice-cream-choice">

      <datalist id="ice-cream-flavors">
        <option value="Chocolate">
        <option value="Coconut">
        <option value="Mint">
        <option value="Strawberry">
        <option value="Vanilla">
      </datalist>
    </fieldset>
    <fieldset>
      <legend>Radio </legend>
      <div class="form-check">
        <input type="radio" id="active" name="active" class="form-check-input" >
        <label for="active" class="form-check-label">Active</label>
      </div>
      <div class="form-check">
        <input type="radio" id="retreated" name="retreated" class="form-check-input"/>
        <label for="retreated" class="form-check-label">Retreated</label>
      </div>

    </fieldset>
    <fieldset>
      <legend>Form - checkbox</legend>
      <%-- Checkbox formulaire can be represented using fieldset  --%>

      <fieldset class="checkbox">
        <legend>Civil Status :   </legend>
        <div class="form-group">
          <input type="checkbox" id="single" name="single" >
          <label for="single">Single</label>
        </div>
        <div class="form-group">
          <input type="checkbox" id="maried" name="maried"/>
          <label for="maried">Maried</label>
        </div>
        <div class="form-group">
          <input type="checkbox" id="divorced" name="divorced"/>
          <label for="divorced">Divorced</label>
        </div>
        <div class="form-group">
          <input type="checkbox" id="widow" name="widow"/>
          <label for="widow">Widow</label>
        </div>
      </fieldset>
    </fieldset>
  </fieldset>
  <fieldset>
    <legend>Form - select</legend>
    <label for="cars">Choose a car:</label>
    <select id="cars" name="cars">
      <option value="volvo">Volvo</option>
      <option value="saab">Saab</option>
      <option value="fiat">Fiat</option>
      <option value="audi">Audi</option>
    </select>
  </fieldset>
  <fieldset>
    <legend>Form - multiple choices</legend>
    <label for="carsLabel">Choose a car:</label>
    <select id="carsLabel" name="cars" multiple>
      <option value="volvo">Volvo</option>
      <option value="saab">Saab</option>
      <option value="fiat">Fiat</option>
      <option value="audi">Audi</option>
    </select>
  </fieldset>
  <fieldset>
    <legend>Form - textarea</legend>
    <label for="myTextInput">Write something</label>
    <textarea name="myText" id="myTextInput" cols="30" rows="10">

    </textarea>
  </fieldset>
  <div class="button-group mt-2">
    <button type="submit" class="btn btn-success">Envoyer</button>
    <button class="btn btn-danger">Annuler</button>
  </div>
</form>