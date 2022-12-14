<?php

namespace Models;

class Student
{
    protected string $firstName;
    protected string $lastName;
    protected string $sex;
    protected string $groupNumber;
    protected string $email;
    protected int $points;
    protected int $birthYear;
    protected bool $local;

    public function __construct($name, $lastName, $sex, $group, $email, $points, $birth, $local)
    {
        $this->firstName = $name;
        $this->lastName = $lastName;
        $this->sex = $sex;
        $this->groupNumber = $group;
        $this->email = $email;
        $this->points = $points;
        $this->birthYear = $birth;
        $this->local = $local;
    }

    public function getFirstName(): string
    {
        return $this->firstName;
    }
    public function setFirstName($firstName): void
    {
        $this->firstName = $firstName;
    }
    public function getLastName(): string
    {
        return $this->lastName;
    }
    public function setLastName($lastName): void
    {
        $this->lastName = $lastName;
    }
    public function getSex(): string
    {
        return $this->sex;
    }
    public function setSex($sex): void
    {
        $this->sex = $sex;
    }
    public function getGroupNumber(): string
    {
        return $this->groupNumber;
    }
    public function setGroupNumber($groupNumber): void
    {
        $this->groupNumber = $groupNumber;
    }
    public function getEmail(): string
    {
        return $this->email;
    }
    public function setEmail($email): void
    {
        $this->email = $email;
    }
    public function getPoints(): int
    {
        return $this->points;
    }
    public function setPoints($points): void
    {
        $this->points = $points;
    }
    public function getBirthYear(): int
    {
        return $this->birthYear;
    }
    public function setBirthYear($birthYear): void
    {
        $this->birthYear = $birthYear;
    }
    public function getIsLocal(): bool
    {
        return $this->local;
    }
    public function setIsLocal($local): void
    {
        $this->local = $local;
    }


}