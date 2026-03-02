<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- SIDEBAR + TOPBAR -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<div class="d-flex flex-column flex-shrink-0 p-3 bg-dark text-white" style="width:260px; min-height:100vh; position:fixed; top:0; left:0;">

    <a href="#" class="d-flex align-items-center mb-3 text-white text-decoration-none">
        <i class="bi bi-shield-lock-fill fs-4 me-2 text-primary"></i>
        <span class="fs-5 fw-semibold">Security App</span>
    </a>

    <hr class="border-secondary">

    <ul class="nav nav-pills flex-column mb-auto">
        <li>
            <a href="#" class="nav-link active bg-primary text-white rounded mb-1">
                <i class="bi bi-house-door me-2"></i> Home
            </a>
        </li>

        <li><p class="nav-link text-secondary text-uppercase fw-bold mb-0 mt-2" style="font-size:.7rem; pointer-events:none;">Management</p></li>
        <li>
            <a href="admin" class="nav-link text-white-50 rounded mb-1">
                <i class="bi bi-people me-2"></i> Users
            </a>
        </li>
        <li>
            <a href="produit" class="nav-link text-white-50 rounded mb-1">
                <i class="bi bi-box-seam me-2"></i> Produits
            </a>
        </li>

        <li><p class="nav-link text-secondary text-uppercase fw-bold mb-0 mt-2" style="font-size:.7rem; pointer-events:none;">Transactions</p></li>
        <li>
            <a href="purchase" class="nav-link text-white-50 rounded mb-1">
                <i class="bi bi-cart3 me-2"></i> Purchases
            </a>
        </li>
        <li>
            <a href="sale" class="nav-link text-white-50 rounded mb-1">
                <i class="bi bi-graph-up-arrow me-2"></i> Sales
            </a>
        </li>
    </ul>

    <hr class="border-secondary">

    <div class="dropdown">
        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown">
            <div class="bg-primary rounded-circle d-flex align-items-center justify-content-center me-2 fw-bold" style="width:34px;height:34px;font-size:.85rem;">AD</div>
            <strong>Admin</strong>
        </a>
        <ul class="dropdown-menu dropdown-menu-dark text-small shadow mb-1">
            <li><a class="dropdown-item" href="#"><i class="bi bi-person me-2"></i>Profile</a></li>
            <li><a class="dropdown-item" href="#"><i class="bi bi-gear me-2"></i>Settings</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item text-danger" href="logout"><i class="bi bi-box-arrow-right me-2"></i>Logout</a></li>
        </ul>
    </div>
</div>