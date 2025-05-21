<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Contact - Grace Bryant</title>
  <%@ include file="header.jsp" %>
  <style>
    body {
      margin: 0;
      padding: 0;
      background-color: #f8f9fa;
    }

    .main {
      padding: 40px 20px;
      max-width: 1000px;
      margin: auto;
    }

    h1, h2 {
      color: #2c3e50;
    }

    .contact-info p,
    .contact-info ul {
      font-size: 16px;
      color: #555;
    }

    .contact-info ul {
      list-style: none;
      padding: 0;
      margin: 20px 0;
    }

    .contact-info li {
      margin-bottom: 10px;
    }

    .contact-form {
      margin-top: 40px;
    }

    .form-group {
      display: flex;
      gap: 10px;
      margin-bottom: 20px;
    }

    input[type="text"],
    input[type="email"],
    textarea {
      width: 100%;
      padding: 12px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 8px;
      box-sizing: border-box;
      font-size: 14px;
    }

    button[type="submit"] {
      background-color: #27ae60;
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      font-size: 16px;
    }

    button[type="submit"]:hover {
      background-color: #219150;
    }

    iframe {
      width: 100%;
      height: 450px;
      border: none;
      border-radius: 10px;
      margin-top: 40px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    .quote {
      text-align: center;
      background-color: #eaf4f4;
      padding: 30px 20px;
      margin-top: 50px;
    }

    blockquote p {
      font-size: 20px;
      font-style: italic;
      color: #333;
    }

    blockquote footer {
      margin-top: 10px;
      font-weight: bold;
      color: #555;
    }
  </style>
</head>
<body>
  <main class="main">
    <section class="contact-info">
      <h1>Contact Us</h1>
      <p>If you have any questions or inquiries, feel free to get in touch with us using the information below or the contact form.</p>
      <ul>
        <li><strong>Office:</strong> Lions Marga, Pokhara 33800 </li>
        <li><strong>Phone:</strong> +977 9876506543</li>
        <li><strong>Email:</strong> Natura@gmail.com</li>
      </ul>
    </section>

    <section class="contact-form">
      <h2>Send a Message</h2>
      <form id="messageForm">
        <div class="form-group">
          <input type="text" placeholder="First Name" required>
          <input type="text" placeholder="Last Name" required>
        </div>
        <input type="email" placeholder="Email" required>
        <textarea placeholder="Comment or Message" rows="5" required></textarea>
        <button type="submit">Send Message</button>
      </form>
    </section>

    <!-- Google Maps Embed -->
    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3515.750394104994!2d83.99786597518833!3d28.214895202923998!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39959434ad2a5bf9%3A0xf4e7f9c749f63113!2sInformatics%20College%20Pokhara!5e0!3m2!1sen!2snp!4v1745249891869!5m2!1sen!2snp" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
  </main>

  <section class="quote">
    <blockquote>
      <p>
        Great things start with small steps. Let your journey begin with a message.
      </p>
      <footer>— Natura</footer>
    </blockquote>
  </section>

</body>
  <%@ include file="footer.jsp" %>
<script>
  function showAlert() {
    alert("Thank you for your message!");
  }

  var form = document.getElementById("messageForm");
  form.onsubmit = function() {
    showAlert();
    return false; // stops the page from reloading
  };
</script>


</html>
