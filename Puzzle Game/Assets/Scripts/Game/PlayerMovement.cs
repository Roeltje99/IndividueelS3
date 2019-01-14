using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(Rigidbody))]
public class PlayerMovement : MonoBehaviour
{
    public float MovementSpeed = 100;

    private float SprintMultiplier = 2f;

    private void FixedUpdate()
    {
        if (Input.GetKey(KeyCode.W))
        {
            transform.position += transform.forward * MovementSpeed * SprintMultiplier * Time.deltaTime;
        }
        if (Input.GetKey(KeyCode.A))
        {
            transform.position += -transform.right * MovementSpeed * SprintMultiplier * Time.deltaTime;
        }
        if (Input.GetKey(KeyCode.S))
        {
            transform.position += -transform.forward * MovementSpeed * SprintMultiplier * Time.deltaTime;
        }
        if (Input.GetKey(KeyCode.D))
        {
            transform.position += transform.right * MovementSpeed * SprintMultiplier * Time.deltaTime;
        }
        if (Input.GetKey(KeyCode.LeftShift))
        {
            SprintMultiplier = 2f;
        }
        else
        {
            SprintMultiplier = 1f;
        }
    }
}
